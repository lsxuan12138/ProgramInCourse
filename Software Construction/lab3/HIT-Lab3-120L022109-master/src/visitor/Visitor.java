package visitor;

import poll.Poll;

public interface Visitor {
    <C> String visit(Poll<C> poll);
}
