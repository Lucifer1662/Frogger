package GameEngine;
import java.nio.FloatBuffer;

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
		
		
		public void UpdateTransformationMatrix() {
		
			Matrix3f.mul(translationMat, rotationMat, transformMat);
			Matrix3f.mul(transformMat, scaleMat, transformMat);
			if(getGameObject().getParent() != null) {
				Matrix3f.mul(getGameObject().getParent().getTransform().transformMat, transformMat, transformMat);
			}
			for(GameObject child : getGameObject().getChildren()) {
				child.getTransform().UpdateTransformationMatrix();
			}
		}
		
		public void Apply(float posx, float posy, float scalex, float scaley, float angleOfRotation){
			_Translate(posx,posy);
			_Scale(scalex, scaley);
			_Rotate(angleOfRotation);
			UpdateTransformationMatrix();
		}
	
		public void Apply(Vector2f pos, Vector2f scale, float angleOfRotation){
			Apply(pos.x, pos.y, scale.x, scale.y, angleOfRotation);
		}
		
		public void setPosition(Vector2f pos) {
			setPosition(pos.x, pos.y);
		}
		
		public void setPosition(float posx, float posy) {
			_Translate(posx, posy);
			UpdateTransformationMatrix();
		}
		
		private void _Translate(float posx, float posy) {
			position.x = posx;
			position.y = posy;
			
			translationMat.loadTranspose(FloatBuffer.wrap(org.newdawn.slick.geom.Transform
					.createTranslateTransform(posx,posy).getMatrixPosition()));
		}
		
		
		
		
		public void setScale(Vector2f scale) {
			setScale(scale.x,scale.y);
		}
		
		public void setScale(float scalex, float scaley) {
			_Scale(scalex, scaley);			
			UpdateTransformationMatrix();
		}
		
		private void _Scale(float scalex, float scaley) {
			scale.x = scalex;
			scale.y = scaley;
			
			scaleMat.loadTranspose(FloatBuffer.wrap(org.newdawn.slick.geom.Transform
					.createScaleTransform(scalex, scaley).getMatrixPosition()));
			
			UpdateTransformationMatrix();
		}
		
		
		public void setRotation(float angle) {
			_Rotate(angle);
			UpdateTransformationMatrix();
		}
		
		private void _Rotate(float angle) {
			rotation = angle;
			rotationMat.loadTranspose(FloatBuffer.wrap(org.newdawn.slick.geom.Transform
					.createRotateTransform(angle).getMatrixPosition()));
		}

		
		
		public void Mult(Vector2f point) {
			Vector3f p = new Vector3f(point.x, point.y, 1);
			Matrix3f.transform(transformMat, p, p);
			point.x = p.x;
			point.y = p.y;
		}
	}
