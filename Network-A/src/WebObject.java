
public class WebObject {
 
	public WebObject(int levels, int startSize, int endSize){
		
	}
	
	private class Node{
		double[] weights;
		double threshold;
		boolean didFire;
		int[][] readBuffer; //should be 1 or -1
		int[][] writeBuffer;
		int writeIndex;
		int readIndex;
		double modificationMultiplier=1.3;
		
		Node(int[][] readBuffer, int[][] writeBuffer, int writeIndex, int readIndex){
			this.readBuffer=readBuffer;
			this.writeBuffer=writeBuffer;
	}
		public void calculate(){
			double sum=0;
			for(int i=0;i<this.writeBuffer[0].length;i++){
				sum+=readBuffer[this.readIndex][i]*this.weights[i];
			}
			if(sum>=this.threshold)
				this.fire();
			else
				this.doNotFire();
		}
		private void fire(){
			this.didFire=true;
			for(int i=0;i<this.writeBuffer.length;i++){
			writeBuffer[i][this.writeIndex]=1;
			}
			
		}
		private void doNotFire(){
			this.didFire=false;
			for(int i=0;i<this.writeBuffer.length;i++){
			writeBuffer[i][this.writeIndex]=-1;
			}
			
		}
		public void enforce(){
			for(int i=0;i<this.writeBuffer[0].length;i++){
				if(readBuffer[this.readIndex][i]==1)
					this.weights[i]=this.weights[i]*this.modificationMultiplier;
				else
					this.weights[i]=this.weights[i]*-1;
				
				}
			}
			
		public void deinforce(){
			for(int i=0;i<this.writeBuffer[0].length;i++){
				if(readBuffer[this.readIndex][i]==1)
					this.weights[i]=this.weights[i]/this.modificationMultiplier;
				else
					this.weights[i]=this.weights[i]*-1;
				
				}
			
		}
		
	}
}
