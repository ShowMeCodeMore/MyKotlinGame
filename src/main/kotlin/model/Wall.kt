package model

import Config
import business.AttackAble
import business.BlockAble
import business.DestoryAble
import business.SufferAble
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter


/**
 * ClassName:Wall
 * Description:砖墙
 */
class Wall(override var x: Int, override var y: Int) : BlockAble, SufferAble, DestoryAble {
    override fun needDestory(): Boolean {
        return blood <= 0
    }

    override var blood: Int = 3

    override fun notifyAttack(attack: AttackAble): Array<View>? {
        //疼
        Composer.play("snd/hit.wav")
        //减少血量
        blood -= attack.attackPower
        //需要爆炸效果
        return arrayOf(Blast(x, y))
    }

    override val code: Int = 2
    override var width: Int = Config.blockSize
    override var height: Int = Config.blockSize

    override fun draw() {
        Painter.drawImage("img/wall.gif", x, y)
    }
}