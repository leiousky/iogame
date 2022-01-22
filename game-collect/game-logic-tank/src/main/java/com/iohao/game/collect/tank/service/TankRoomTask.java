package com.iohao.game.collect.tank.service;

import com.iohao.game.collect.common.FrameKit;
import com.iohao.game.collect.common.room.RoomService;
import com.iohao.game.collect.proto.tank.TankRoomBroadcastRes;
import com.iohao.game.collect.tank.action.TankAction;
import com.iohao.game.collect.tank.mapstruct.TankMapstruct;
import com.iohao.game.collect.tank.room.TankPlayerEntity;
import com.iohao.game.collect.tank.room.TankRoomEntity;
import com.iohao.little.game.common.kit.ExecutorKit;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 洛朱
 * @date 2022-01-15
 */
public class TankRoomTask {

    RoomService roomService = RoomService.me();

    final ScheduledExecutorService scheduled = ExecutorKit.newSingleScheduled("房间内广播");

    public void init() {

        int framePerMillis = FrameKit.framePerMillis(20);

        scheduled.scheduleAtFixedRate(new TaskRoomStart()
                , 5000
                , framePerMillis
                , TimeUnit.MILLISECONDS);

    }


    static class TaskRoomStart implements Runnable {
        RoomService roomService = RoomService.me();

        @Override
        public void run() {
            // 所有房间
//            Collection<TankRoom> rooms = roomService.listRoom();

            long roomId = TankAction.tempRoomId;
            TankRoomEntity room = roomService.getRoom(roomId);
            if (Objects.isNull(room)) {
                return;
            }

            Collection<TankPlayerEntity> listPlayer = room.listPlayer();
            TankRoomBroadcastRes res = new TankRoomBroadcastRes();
            TankMapstruct.ME.convertList(listPlayer);

            // 转发当前



        }
    }

    public static TankRoomTask me() {
        return Holder.ME;
    }

    /** 通过 JVM 的类加载机制, 保证只加载一次 (singleton) */
    private static class Holder {
        static final TankRoomTask ME = new TankRoomTask();
    }
}