package ast;
public class VarRefNode implements ASTNode {
    public final String name;
    public VarRefNode(String name) { this.name = name; }
    public <T> T accept(ASTVisitor<T> v) { return v.visit(this); }
}
