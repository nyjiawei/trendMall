package com.fjw.coolerMall.config.lister;
import com.fjw.coolerMall.service.delegate.impl.UserBaseServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * 自定义监听器,用于监听订单没有在规定时间内付款导致失效
 */
@Component
public class OrderNoExpirationListener extends KeyExpirationEventMessageListener {
    Logger log = LogManager.getLogger(OrderNoExpirationListener.class);

    public OrderNoExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
        log.info("订单号：[" + expiredKey + "]，在规定时间内未支付，已经失效");
    }

}
