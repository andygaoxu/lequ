package com.lequ.server.bootstrap.util;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.lequ.server.bootstrap.model.PacketSplitEntity;

public class RoundRobinPacket  {

    public static final String NAME = "roundrobin"; 
    
    private final ConcurrentMap<String, AtomicPositiveInteger> sequences = new ConcurrentHashMap<String, AtomicPositiveInteger>();

    public PacketSplitEntity doSelect(List<PacketSplitEntity> packetSplits,String packetId) {
    	String key = packetId;
        int length = packetSplits.size(); // 总个数

        AtomicPositiveInteger sequence = sequences.get(key);
        if (sequence == null) {
            sequences.putIfAbsent(key, new AtomicPositiveInteger());
            sequence = sequences.get(key);
        }
        // 取模轮循
        return packetSplits.get(sequence.getAndIncrement() % length);
    }

}