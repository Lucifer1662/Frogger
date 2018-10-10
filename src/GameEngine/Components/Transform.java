package GameEngine.Components;

import java.nio.FloatBuffer;

import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.geom.Vector2f;

import GameEngine.Window;
import GameEngine.Core.Component;
import GameEngine.Core.GameObject;

/**
 * Used by GameObject to represent position, scale and rotation and to achieve
 * relative positions, scale and rotation
 * 
 * @author Luke Hawkins
 */
public class Transform {
	private Matrix3f translationMat = new Matrix3f();
	private Matrix3f rotationMat = new Matrix3f();
	private Matrix3f scaleMat = new Matrix3f();
	private Matrix3f transformMat = new Matrix3f();
	private GameObject gameObject;

	private Vector2f position = new Vector2f();
	private float rotation = 0;
	private Vector2f scale = new Vector2f(1, 1);
	private Vector2f worldPosition = new Vector2f();
	private Vector2f worldScale = new Vector2f(1, 1);

	/**
	 * Constructs a Transform on a GameObject with a starting position, scale and
	 * angle of rotation
	 * 
	 * @param gameObject      The GameObject attached to
	 * @param pos             The position on start
	 * @param scale           The scale on start
	 * @param angleOfRotation The angle of rotation on start
	 */
	public Transform(GameObject gameObject, Vector2f pos, Vector2f scale, float angleOfRotation) {
		this(gameObject);
		setPosition(pos);
		setScale(scale);
		setRotation(angleOfRotation);
	}

	/**
	 * Constructs a transform with the GameObject as its parent, its initial
	 * position is 0,0, scale 1,1 and rotation 0
	 * 
	 * @param gameObject The GameObject attached to
	 */
	public Transform(GameObject gameObject) {
		this.gameObject = gameObject;
		scale(1, 1);
	}

	/**
	 * Gets the local position
	 * 
	 * @return The local position
	 */
	public Vector2f getPosition() {
		return new Vector2f(position);
	}

	/**
	 * Gets the local scale
	 * 
	 * @return The local scale
	 */
	public Vector2f getScale() {
		return new Vector2f(scale);
	}

	/**
	 * Gets the local rotation
	 * 
	 * @return The local rotation
	 */
	public float getRotation() {
		return rotation;
	}

	/**
	 * Gets the position in world space
	 * 
	 * @return The position in world space
	 */
	public Vector2f getWorldPosition() {
		return new Vector2f(worldPosition);
	}

	/**
	 * Gets the scale in world space
	 * 
	 * @return The scale in world space
	 */
	public Vector2f getWorldScale() {
		return new Vector2f(worldScale);
	}

	/**
	 * Updates transformation matrix for itself and all of the gameObjects children,
	 * so they are still all relative to their parents
	 */
	public void UpdateTransformationMatrix() {
		// Concatenate matrices into transform
		// transform = parentTransform * translate * rotation * scale

		// translate * rotation
		Matrix3f.mul(translationMat, rotationMat, transformMat);
		// translate * rotation * scale
		Matrix3f.mul(transformMat, scaleMat, transformMat);

		// if i am not a camera
		if (gameObject.getParent() != null) {
			Transform parentTransform = gameObject.getParent().getTransform();
			// parentTransform * translate * rotation * scale
			Matrix3f.mul(parentTransform.transformMat, transformMat, transformMat);

			//
			if (gameObject.getParent().getParent() != null) {
				// update world position and world scale if there is a parent
				worldScale.x = parentTransform.worldScale.x * scale.x;
				worldScale.y = parentTransform.worldScale.y * scale.y;
				worldPosition.x = parentTransform.worldPosition.x + position.x * worldScale.x;
				worldPosition.y = parentTransform.worldPosition.y + position.y * worldScale.y;
			} else {
				// set world position and world scale to local position and scale
				worldScale.x = scale.x;
				worldScale.y = scale.y;
				worldPosition.x = position.x;
				worldPosition.y = position.y;
			}

		} else {
			// set world position and world scale to local position and scale
			worldScale.x = scale.x;
			worldScale.y = scale.y;
			worldPosition.x = position.x;
			worldPosition.y = position.y;
		}
		// update transform for children as well
		for (GameObject child : gameObject.getChildren()) {
			child.getTransform().UpdateTransformationMatrix();
		}
	}

	/**
	 * Applies all the transformations and then updates the transformation matrix
	 * and children
	 * 
	 * @param posx            The position in the x direction
	 * @param posy            The position in the y direction
	 * @param scalex          The scale in the x direction
	 * @param scaley          The scale in the y direction
	 * @param angleOfRotation The angle of rotation
	 */
	public void Apply(float posx, float posy, float scalex, float scaley, float angleOfRotation) {
		translate(posx, posy);
		scale(scalex, scaley);
		rotate(angleOfRotation);
		UpdateTransformationMatrix();
	}

	/**
	 * Applies all the transformations and then updates the transformation matrix
	 * and children
	 * 
	 * @param pos             The position
	 * @param scale           The scale
	 * @param angleOfRotation The angle of rotation
	 */
	public void Apply(Vector2f pos, Vector2f scale, float angleOfRotation) {
		Apply(pos.x, pos.y, scale.x, scale.y, angleOfRotation);
	}

	/**
	 * Sets the position but in screen space
	 * 
	 * @param x The position in the x direction in screen space
	 * @param y The position in the y direction in screen space
	 */
	public void setPositionPixel(float x, float y) {
		float conversion = Window.getPixelToUnit(gameObject.getScene());
		setPosition(x * conversion, y * conversion);
	}

	/**
	 * Sets the position and update transformation matrix for itself and children
	 * 
	 * @param pos The position in screen space
	 */
	public void setPosition(Vector2f pos) {
		setPosition(pos.x, pos.y);
	}

	/**
	 * Sets the position and update transformation matrix for itself and children
	 * 
	 * @param posx The position to be set in the x direction
	 * @param posy The position to be set in the y direction
	 */
	public void setPosition(float posx, float posy) {
		// does quick check to make sure something actually happens
		if (Float.compare(posx, position.x) != 0 || Float.compare(posy, position.y) != 0) {
			// translates and updates
			translate(posx, posy);
			UpdateTransformationMatrix();
		}
	}

	private void translate(float posx, float posy) {
		position.x = posx;
		position.y = posy;

		translationMat.loadTranspose(FloatBuffer
				.wrap(org.newdawn.slick.geom.Transform.createTranslateTransform(posx, posy).getMatrixPosition()));
	}

	/**
	 * Sets the scale and update transformation matrix for itself and children
	 * 
	 * @param scale The scale to be set
	 */
	public void setScale(Vector2f scale) {
		setScale(scale.x, scale.y);
	}

	/**
	 * Sets the scale and update transformation matrix for itself and children
	 * 
	 * @param scalex The scale in the x direction
	 * @param scaley The scale in the y direction
	 */
	public void setScale(float scalex, float scaley) {
		if (Float.compare(scalex, scale.x) != 0 || Float.compare(scaley, scale.y) != 0) {
			scale(scalex, scaley);
			UpdateTransformationMatrix();
		}
	}

	/**
	 * Sets the scale but does not update
	 */
	private void scale(float scalex, float scaley) {
		scale.x = scalex;
		scale.y = scaley;

		scaleMat.loadTranspose(FloatBuffer
				.wrap(org.newdawn.slick.geom.Transform.createScaleTransform(scalex, scaley).getMatrixPosition()));
	}

	/**
	 * Sets the rotation and update transformation matrix for itself and children
	 * 
	 * @param angle The angle of rotation to be set
	 */
	public void setRotation(float angle) {
		if (Float.compare(angle, rotation) != 0) {
			rotate(angle);
			UpdateTransformationMatrix();
		}
	}

	/*
	 * Sets the rotation but does not update
	 */
	private void rotate(float angle) {
		rotation = angle;
		rotationMat.loadTranspose(
				FloatBuffer.wrap(org.newdawn.slick.geom.Transform.createRotateTransform(angle).getMatrixPosition()));
	}

	/**
	 * Transforms point by transformation matrix and stores the result in the same
	 * point
	 * 
	 * @param point The point to be transformed
	 */
	public void transform(Vector2f point) {
		// Converts to vector 3
		Vector3f p = new Vector3f(point.x, point.y, 1);
		// transform
		Matrix3f.transform(transformMat, p, p);
		// return
		point.x = p.x;
		point.y = p.y;
	}

	/**
	 * transforms point by transformation matrix and stores the result in result
	 * 
	 * @param point  The point to be transform
	 * @param result The result of the point being transformed
	 */
	public void transform(Vector2f point, Vector2f result) {
		// Convert to vector 3
		Vector3f p = new Vector3f(point.x, point.y, 1);
		// transform
		Matrix3f.transform(transformMat, p, p);
		// return
		result.x = p.x;
		result.y = p.y;
	}
}
