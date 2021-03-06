package com.utilitybelt.test.algorithmics;

public class TesterHelpers {
	public static class TestNumber {
		private int i;
		
		public TestNumber(int i) {
			this.i = i;
		}
		
		public int getI() {
			return i;
		}
		
		@Override
		public String toString() {
			return "" + i;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + i;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TestNumber other = (TestNumber) obj;
			if (i != other.i)
				return false;
			return true;
		}		
	}

	public static class ComparableNumber implements Comparable<ComparableNumber> {

		private int i;
		public ComparableNumber(int i) {
			this.i = i;
		}
		
		public int getI() {
			return i;
		}

		@Override
		public int compareTo(ComparableNumber o) {
			if(i < o.i) {
				return -1;
			}
			if(i > o.i) {
				return 1;
			}
			return 0;
		}

		@Override
		public String toString() {
			return "" + i;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + i;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ComparableNumber other = (ComparableNumber) obj;
			if (i != other.i)
				return false;
			return true;
		}
	}
}
