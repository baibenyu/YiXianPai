package com.baiyu.entry;

import com.baiyu.frame.Player;

public interface Attackable {
    void attack(Player me,Player target,int attackValue);
}
