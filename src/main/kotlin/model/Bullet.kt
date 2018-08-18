package model

import Config
import business.AttackAble
import business.AutoMoveAble
import business.DestoryAble
import business.SufferAble
import enums.Direction
import org.itheima.kotlin.game.core.Painter


/**
 * ClassName:Bullet
 * Description:子弹
 */
class Bullet(override val owner: View, override var direction: Direction, block: (Int, Int) -> Pair<Int, Int>) : AutoMoveAble, DestoryAble, AttackAble, SufferAble {
    override var blood: Int = 1

    override fun notifyAttack(attack: AttackAble): Array<View>? {
        needDestory = true
        return null
    }

    override var attackPower: Int = 1
    var needDestory = false
    override fun checkAttack(suffer: SufferAble): Boolean {
        //没有碰撞
        if (y >= suffer.y + suffer.height || y + height <= suffer.y || suffer.x + suffer.width <= x || x + width <= suffer.x) {
            return false
        }
        return true
    }

    override fun notifyAttack(suffer: SufferAble) {
        needDestory = true
    }

    override fun needDestory(): Boolean {
        //越界销毁
        if (x < 0 || y < 0 || x > Config.gameWidth || y > Config.gameHeight) {
            return true
        }
        //攻击销毁
        if (needDestory) {
            return true
        }
        return false
    }

    override fun autoMove() {
        when (direction) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }
    }

    override val speed: Int = 8

    override val code: Int = 2
    override var width: Int = 0
    override var height: Int = 0
    override var x: Int = 0
    override var y: Int = 0

    val imgPath by lazy {
        when (direction) {
            Direction.UP -> "img/bullet_u.gif"
            Direction.DOWN -> "img/bullet_d.gif"
            Direction.LEFT -> "img/bullet_l.gif"
            Direction.RIGHT -> "img/bullet_r.gif"
        }
    }

    init {
        //计算出当前子弹宽度和高度
        val size = Painter.size(imgPath)
        width = size[0]
        height = size[1]
        //调用block函数传递宽度和高度
        val pair = block(width, height)
        x = pair.first
        y = pair.second
    }

    //方向
    override fun draw() = Painter.drawImage(imgPath, x, y)
}