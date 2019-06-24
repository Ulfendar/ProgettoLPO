package interpreterL.visitors.evaluation;

import java.util.*;

public class SetValue extends PrimValue<Set<Value>>  {

    public SetValue(Set<Value> elem) {
        super(elem);

        /*Set<Value> set = new LinkedHashSet<>();
        set.addAll(value);
        value.clear();
        value.addAll(set);*/

    }

    public Set<Value> getValues() {
        return value;
    }

    public SetValue Intersection(SetValue elem) {

        Set<Value> set = new HashSet<>(value);
        set.retainAll(elem.getValues());

        return new SetValue(new HashSet<>(set));
    }

    public SetValue Union(SetValue elem) {

        Set<Value> set = new HashSet<>(value);
        set.addAll(elem.getValues());

        return new SetValue(new HashSet<>(set));
    }

    public int Size(){
        return value.size();
    }


    public void add(Value val){
        value.add(val);
    }

    public void remove(Value val){
        value.remove(val);
    }

    public boolean isIn(Value val){
        return value.contains(val);

    }



    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof SetValue))
            return false;
        SetValue op = (SetValue) obj;

        return value.equals(op.value);
    }

    @Override
    public SetValue asSet() {
        return this;
    }

    @Override
    public String toString() {
     
        String string = "{ ";
        Iterator <Value> iter = value.iterator();
        if(iter.hasNext()) string += iter.next().toString()+" ";
        while(iter.hasNext())
            string+=", "+iter.next().toString()+" ";
        string += "}";

        return string;
    }



}
