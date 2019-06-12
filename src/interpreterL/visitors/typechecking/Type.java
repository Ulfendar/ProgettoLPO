package interpreterL.visitors.typechecking;

import static interpreterL.visitors.typechecking.PrimtType.SET;
import static interpreterL.visitors.typechecking.PrimtType.STRING;

public interface Type {
	default Type checkEqual(Type found) throws TypecheckerException {
		System.out.println(1);
		if (!equals(found))
			throw new TypecheckerException(found.toString(), toString());
		return this;
	}

	default Type checkEqualIn(Type found) throws TypecheckerException {
		System.out.println(2);
		if (!equals(found))
			throw new TypecheckerException(found.toString(), found.toString()+" SET");
		return this;
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

	default void checkIsSetType() throws TypecheckerException {
		System.out.println(5);
		if (!(this instanceof SetType)) {
			throw new TypecheckerException(toString(), toString()+ " "+SET);
		}
	}

	default Type getFstPairType() throws TypecheckerException {
		System.out.println(6);
		checkIsPairType();
		return ((PairType) this).getFstType();
	}

	default Type getSetType() throws TypecheckerException {
		System.out.println(7);
		checkIsSetType();
		return ((SetType) this).getElemType();
	}

	default void checkSetOf() throws TypecheckerException {
		System.out.println(11);
		if (!(this instanceof SetType))
			throw new TypecheckerException(toString(), toString()+" "+SetType.TYPE_NAME);

	}

	default Type getSndPairType() throws TypecheckerException {
		System.out.println(8);
		checkIsPairType();
		return ((PairType) this).getSndType();
	}
}
