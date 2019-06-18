package interpreterL.parser.ast;

import interpreterL.visitors.Visitor;

import java.util.HashSet;
import java.util.Set;

public class ExpList implements ExpSeq {
    private Set<Exp> exps = new HashSet<>();

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
