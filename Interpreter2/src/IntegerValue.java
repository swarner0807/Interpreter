/**
 * Class that represents an integer value.
 * @author Dave Reed
 * @version 2/8/14
 */
public class IntegerValue implements DataValue {
		public int value;
		
		/**
		 * Constructs an integer value.
		 *   @param num the number being stored 
		 */
		public IntegerValue(int num) {
			this.value = num;
		}
		
		/**
		 * Accesses the stored integer value.
		 *   @return the integer value (as an Object)
		 */
		public Object getValue() {
			return new Integer(this.value);
		}
		
		/**
		 * Converts the integer value to a boolean.
		 *   @return true if value is nonzero; else false
		 */
		public boolean toBoolean() {
			return this.value != 0;
		}

		/**
		 * Converts the integer value to a String.
		 *   @return a String representation of the integer value
		 */
		public String toString() {
			return ""+this.value;
		}
	}