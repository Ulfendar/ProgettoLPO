package interpreterL.parser.ast;

import interpreterL.visitors.Visitor;

import java.util.ArrayList;
import java.util.List;

public class ExpList implements ExpSeq {
    private List<Exp> exps = new ArrayList<>();

    public ExpList(Exp exp){
        add(exp);
    }

    public void add(Exp exp){
        exps.add(exp);
    }

    public boolean isIn(Exp exp) {return exps.contains(exp);}

    @Override
    public <T> T accept(Visitor<T> visitor) {

        return visitor.visitExpList(exps);
    }
}
