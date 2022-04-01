package com.iohao.game.domain.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户钱包
 *
 * @author shen
 * @Date 2022/3/28
 * @Slogan  慢慢变好，是给自己最好的礼物
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserWallet implements Serializable {
    String userId;
    String name;
    BigDecimal balance;
}