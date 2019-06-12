package interpreterL.visitors.typechecking;


public class SetType implements Type {

    private final Type elemType;

    public static final String TYPE_NAME = "SET";


    public SetType(Type elemType) {
        this.elemType = elemType;

    }

    public Type getElemType(){
        return elemType;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof SetType))
            return false;
        SetType pt = (SetType) obj;
        return elemType.equals(pt.elemType);
    }

    //@Override
   // public int hashCode() {
    //    return fstType.hashCode() + 31 * sndType.hashCode();
    //}

    @Override
    public String toString() {
        return elemType + " SET";
    }

}
