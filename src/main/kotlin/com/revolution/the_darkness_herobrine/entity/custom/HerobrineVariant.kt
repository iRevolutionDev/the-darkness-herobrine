package com.revolution.the_darkness_herobrine.entity.custom

import java.util.Arrays

enum class HerobrineVariant(val id: Int) {
    DEFAULT(0),
    CLASSIC(1);

    companion object {
        fun byId(typeVariant: Int): HerobrineVariant {
            return Arrays.stream(entries.toTypedArray()).filter { variant: HerobrineVariant -> variant.id == typeVariant }.findFirst().orElse(DEFAULT)
        }
    }
}

fun HerobrineVariant.getTextureLocation(): String {
    return when (this) {
        HerobrineVariant.DEFAULT -> "textures/entity/default/herobrine.png"
        HerobrineVariant.CLASSIC -> "textures/entity/classic/herobrine.png"
    }
}