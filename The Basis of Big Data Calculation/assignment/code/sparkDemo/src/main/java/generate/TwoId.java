package generate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Random;

/**
 * TODO
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-12-18 14:50
 */
public class TwoId {
    private int userId;
    private int followerId;
    private static final int MAX = 10000;
    private static final int MIN = 100;
    public TwoId() {
        Random random = new Random();
        this.userId = random.nextInt(MAX - MIN + 1) + MIN;
        this.followerId = random.nextInt(MAX - MIN + 1) + MIN;
    }

    @Override
    public String toString() {
        return userId+"\t"+followerId+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoId twoId = (TwoId) o;
        return new EqualsBuilder().append(userId, twoId.userId).append(followerId, twoId.followerId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(userId).append(followerId).toHashCode();
    }
}
