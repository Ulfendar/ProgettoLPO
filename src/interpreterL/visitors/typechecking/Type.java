package interpreterL.visitors.typechecking;

import static interpreterL.visitors.typechecking.PrimtType.STRING;

public interface Type {
	default Type checkEqual(Type found) throws TypecheckerException {

		if (!equals(found)) {
			throw new TypecheckerException(found.toString(), toString());
		}
		return this;
	}

	default boolean checkEqualIn(Type found) throws TypecheckerException {

		return equals(found);
	}

	default Type checkEquals() throws TypecheckerException {

		if (!(this instanceof SetType) && !equals(STRING))
			throw new TypecheckerException(this.toString() , SetType.TYPE_NAME + " OR " +  STRING);
		return this;
	}

	default void checkIsPairType() throws TypecheckerException {

		if (!(this instanceof PairType))
			throw new TypecheckerException(toString(), PairType.TYPE_NAME);
	}



	default void checkIsSetTypeOf(Type elem) throws TypecheckerException {

		if (!(this instanceof SetType) || !(this.getSetType().checkEqualIn(elem))) {
			throw new TypecheckerException(toString(), elem.toString()+" "+SetType.TYPE_NAME);
		}
	}

	default Type getFstPairType() throws TypecheckerException {

		checkIsPairType();
		return ((PairType) this).getFstType();
	}

	default void checkIsSetType() throws TypecheckerException {

		if (!(this instanceof SetType)) {
			throw new TypecheckerException(toString(), SetType.TYPE_NAME);
		}
	}

	default Type getSetType() throws TypecheckerException {

		checkIsSetType();
		return ((SetType) this).getElemType();
	}

	default void checkSetOf(Type elem) throws TypecheckerException {

		if (!(this instanceof SetType)||!equals(elem))
			throw new TypecheckerException(toString(), elem +" "+ SetType.TYPE_NAME);

	}

	default Type getSndPairType() throws TypecheckerException {

		checkIsPairType();
		return ((PairType) this).getSndType();
	}
}
