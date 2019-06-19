package interpreterL.visitors.typechecking;

import static interpreterL.visitors.typechecking.PrimtType.STRING;

public interface Type {
	default Type checkEqual(Type found) throws TypecheckerException {
		System.out.println(1);
		if (!equals(found)) {
			System.out.println("err checkEqualsIn");
			throw new TypecheckerException(found.toString(), toString());
		}
		return this;
	}

	default boolean checkEqualIn(Type found) throws TypecheckerException {
		System.out.println(2);
		return equals(found);
	}

	default Type checkEquals() throws TypecheckerException {
		System.out.println(3);
		if (!(this instanceof SetType) && !equals(STRING))
			throw new TypecheckerException(this.toString() , SetType.TYPE_NAME + " OR " +  STRING);
		return this;
	}

	default void checkIsPairType() throws TypecheckerException {
		System.out.println(4);
		if (!(this instanceof PairType))
			throw new TypecheckerException(toString(), PairType.TYPE_NAME);
	}



	default void checkIsSetTypeOf(Type elem) throws TypecheckerException {
		System.out.println(5);
		if (!(this instanceof SetType) || !(this.getSetType().checkEqualIn(elem))) {
			throw new TypecheckerException(toString(), elem.toString()+" "+SetType.TYPE_NAME);
		}
	}

	default Type getFstPairType() throws TypecheckerException {
		System.out.println(6);
		checkIsPairType();
		return ((PairType) this).getFstType();
	}

	default void checkIsSetType() throws TypecheckerException {
		System.out.println(5);
		if (!(this instanceof SetType)) {
			throw new TypecheckerException(toString(), SetType.TYPE_NAME);
		}
	}

	default Type getSetType() throws TypecheckerException {
		System.out.println(7);
		checkIsSetType();
		return ((SetType) this).getElemType();
	}

	default void checkSetOf(Type elem) throws TypecheckerException {
		System.out.println(11);
		if (!(this instanceof SetType)||!equals(elem))
			throw new TypecheckerException(toString(), elem +" "+ SetType.TYPE_NAME);

	}

	default Type getSndPairType() throws TypecheckerException {
		System.out.println(8);
		checkIsPairType();
		return ((PairType) this).getSndType();
	}
}
