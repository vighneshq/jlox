package lox;

abstract class Expr
{
    Expr left, right, expression;
    Token op;
    Object val;
    abstract <T> T accept(Visitor<T> vis);

    interface Visitor<T>
    {
        T visitBinary(Binary expr);
        T visitUnary(Unary expr);
        T visitLiteral(Literal expr);
        T visitGrouping(Grouping expr); 
    }
   
    static class Binary extends Expr
    {
        Binary(Expr left, Token op, Expr right)
        {
            this.left = left;
            this.op = op;
            this.right = right;
        }

        @Override
        <T> T accept(Visitor<T> vis)
        {
           return vis.visitBinary(this);
        }
    }

    static class Unary extends Expr
    {
        Unary(Token op, Expr right)
        {
            this.op = op;
            this.right = right;
        }

        @Override
        <T> T accept(Visitor<T> vis)
        {
            return vis.visitUnary(this);
        }
    }

    static class Literal extends Expr
    {
        Literal(Object val)
        {
            this.val = val;
        }

        @Override
        <T> T accept(Visitor<T> vis)
        {
            return vis.visitLiteral(this);
        }
    }

    static class Grouping extends Expr
    {
        Grouping(Expr expression)
        {
            this.expression = expression;
        }

        @Override
        <T> T accept(Visitor<T> vis)
        {
            return vis.visitGrouping(this);
        }
    }
}
 