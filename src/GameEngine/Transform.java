package GameEngine;
import java.nio.FloatBuffer;

import org.lwjgl.util.vector.Matrix2f;
import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.geom.Vector2f;


public class Transform extends Component{
		private Matrix3f translationMat;
		private Matrix3f rotationMat;
		private Matrix3f scaleMat;
		private Matrix3f transformMat;
		
		
		private Vector2f position;
		private float rotation;
		private Vector2f scale;
		private Vector2f worldPosition;

		
		public Transform(Vector2f pos, Vector2f scale, float angleOfRotation){
			InitializeProperties();
			setPosition(pos);
			setScale(scale);
			setRotation(angleOfRotation);
		}
		
		
		public Transform(){
			InitializeProperties();			
		}
		
		private void InitializeProperties() {
			position = new Vector2f();
			scale = new Vector2f();
			translationMat = new Matrix3f();
			rotationMat = new Matrix3f();
			scaleMat = new Matrix3f();
			transformMat = new Matrix3f();	
			worldPosition = new Vector2f();
		}
		
		
		public Vector2f getPosition() {
			return new Vector2f(position);
		}
		public Vector2f getScale() {
			return new Vector2f(scale);
		}
		public float getRotation() {
			return rotation;
		}
		public Vector2f getWorldPosition() {
			return new Vector2f(worldPosition);
		}
		
		
		public void UpdateTransformationMatrix() {
			Matrix3f.mul(translationMat, rotationMat, transformMat);
			Matrix3f.mul(transformMat, scaleMat, transformMat);
			if(getGameObject().getParent() != null) {
				Transform parentTransform = getGameObject().getParent().getTransform();
				Matrix3f.mul(parentTransform.transformMat, transformMat, transformMat);
				worldPosition = parentTransform.worldPosition.add(position);
			}
			for(GameObject child : getGameObject().getChildren()) {
				child.getTransform().UpdateTransformationMatrix();
			}
		}
		
		public void Apply(float posx, float posy, float scalex, float scaley, float angleOfRotation){
			translate(posx,posy);
			scale(scalex, scaley);
			rotate(angleOfRotation);
			UpdateTransformationMatrix();
		}
	
		public void Apply(Vector2f pos, Vector2f scale, float angleOfRotation){
			Apply(pos.x, pos.y, scale.x, scale.y, angleOfRotation);
		}
		
		public void setPosition(Vector2f pos) {
			setPosition(pos.x, pos.y);
		}
		
		public void setPosition(float posx, float posy) {
			translate(posx, posy);
			UpdateTransformationMatrix();
		}
		
		private void translate(float posx, float posy) {
			position.x = posx;
			position.y = posy;
			
			translationMat.loadTranspose(FloatBuffer.wrap(org.newdawn.slick.geom.Transform
					.createTranslateTransform(posx,posy).getMatrixPosition()));
		}
		
		
		
		
		public void setScale(Vector2f scale) {
			setScale(scale.x,scale.y);
		}
		
		public void setScale(float scalex, float scaley) {
			scale(scalex, scaley);			
			UpdateTransformationMatrix();
		}
		
		private void scale(float scalex, float scaley) {
			scale.x = scalex;
			scale.y = scaley;
			
			scaleMat.loadTranspose(FloatBuffer.wrap(org.newdawn.slick.geom.Transform
					.createScaleTransform(scalex, scaley).getMatrixPosition()));
		}
		
		
		public void setRotation(float angle) {
			rotate(angle);
			UpdateTransformationMatrix();
		}
		
		private void rotate(float angle) {
			rotation = angle;
			rotationMat.loadTranspose(FloatBuffer.wrap(org.newdawn.slick.geom.Transform
					.createRotateTransform(angle).getMatrixPosition()));
		}

		
		
		public void transform(Vector2f point) {
			Vector3f p = new Vector3f(point.x, point.y, 1);
			Matrix3f.transform(transformMat, p, p);
			point.x = p.x;
			point.y = p.y;
		}
		
		public void transform(Vector2f point, Vector2f res) {
			Vector3f p = new Vector3f(point.x, point.y, 1);
			Matrix3f.transform(transformMat, p, p);
			res.x = p.x;
			res.y = p.y;
		}
	}
