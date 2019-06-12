package interpreterL.visitors.evaluation;

public class StringValue extends PrimValue<String>{

    public StringValue(String value) {
        super(value);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof StringValue))
            return false;
        return value.equals(((StringValue) obj).value);
    }

    public int Size(){ return value.length();}

    @Override
    public String asString() {
        return value;
    }

}
