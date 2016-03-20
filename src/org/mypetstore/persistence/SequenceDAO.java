package org.mypetstore.persistence;

import org.mypetstore.domain.Sequence;

/**
 * Created by Mr.Wan on 2016/3/20.
 */
public interface SequenceDAO {

    Sequence getSequence(Sequence sequence);

    void updateSequence(Sequence sequence);
}
