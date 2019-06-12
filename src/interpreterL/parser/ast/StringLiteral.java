package interpreterL.parser.ast;

import interpreterL.visitors.Visitor;

public class StringLiteral extends PrimLiteral<String>{

    public StringLiteral(String val){super(val);}

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitStringLiteral(value);
    }

}
