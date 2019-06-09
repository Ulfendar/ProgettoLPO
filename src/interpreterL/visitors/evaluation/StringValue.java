package interpreterL.visitors.evaluation;

public class StringValue extends PrimValue<String> {

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

    @Override
    public String toString() {
        return value;
    }
}