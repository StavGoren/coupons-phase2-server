package com.stav.server.timertask;

import com.stav.server.logic.CouponsLogic;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    private CouponsLogic couponsLogic;

    public MyTimerTask() {
        this.couponsLogic = new CouponsLogic();
    }


    @Override
    public void run() {
        try {
            couponsLogic.removeExpiredCoupon();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
