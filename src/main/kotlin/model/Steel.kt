package model

import Config
import business.AttackAble
import business.BlockAble
import business.SufferAble
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter


/**
 * ClassName:Wall
 * Description:铁墙
 */
class Steel(override var x: Int, override var y: Int) : BlockAble, SufferAble {
    override var blood: Int = 1

    override fun notifyAttack(attack: AttackAble): Array<View>? {
        Composer.play("snd/hit.wav")
        return null
    }

    override val code: Int = 2
    override var width: Int = Config.blockSize
    override var height: Int = Config.blockSize

    override fun draw() {
        Painter.drawImage("img/steel.gif", x, y)
    }
}