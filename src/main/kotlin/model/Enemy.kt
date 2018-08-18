package model

import Config
import business.*
import enums.Direction
import org.itheima.kotlin.game.core.Painter
import java.util.*


/**
 * ClassName:Enemy
 * Description:敌方坦克
 */
class Enemy(override var x: Int, override var y: Int) : AutoMoveAble, MoveAble, BlockAble, AutoShotAble, SufferAble, DestoryAble {
    override fun needDestory(): Boolean {
        return blood <= 0
    }

    override var blood: Int = 3

    override fun notifyAttack(attack: AttackAble): Array<View>? {
        //如果攻击者所有者是敌机  不用处理
        if (attack.owner is Enemy) return null
        //血量
        blood -= attack.attackPower
        return arrayOf(Blast(x, y))
    }

    override val timeLimit: Int = 1000

    var startTime = System.currentTimeMillis()//0

    override fun autoShot(): Bullet? {
        //调用时间
        var curTime = System.currentTimeMillis()//1005

        //计算x和y
        var bulletX = 0
        var bulletY = 0
        if (curTime - startTime > timeLimit) {
            //时间重新赋值
            startTime = curTime
            return Bullet(this, direction) { bulletWidth, bulletHeight ->
                when (direction) {
                    Direction.UP -> {
                        bulletX = x + (width - bulletWidth) / 2
                        bulletY = y - bulletHeight / 2
                    }
                    Direction.DOWN -> {
                        bulletX = x + (width - bulletWidth) / 2
                        bulletY = y + height - bulletHeight / 2
                    }
                    Direction.LEFT -> {
                        bulletX = x - bulletWidth / 2
                        bulletY = y + (height - bulletHeight) / 2

                    }
                    Direction.RIGHT -> {
                        bulletX = x + width - bulletWidth / 2
                        bulletY = y + (height - bulletHeight) / 2
                    }
                }
                bulletX to bulletY
            }
        }
        return null
    }

    override var badBlock: BlockAble? = null
    override var badDirection: Direction? = null

    override val speed: Int = 4

    override fun autoMove() {
        //如果碰撞  停下来
        if (direction == badDirection) {
            //改变方向
            direction = randomDirection(badDirection)
            return
        }
        when (direction) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }

    }

    /**
     * 返回一个新的方向
     * @param badDirection 碰撞的方向 避免返回碰撞的方向
     */
    private fun randomDirection(badDirection: Direction?): Direction {
        val random = Random().nextInt(4)
        //方向数组
        val values = Direction.values()
        val direction = values[random]
        //是否是碰撞的方向
        if (direction == badDirection) {
            return randomDirection(badDirection)
        }
        return direction
    }

    override val code: Int = 1
    override var width: Int = Config.blockSize
    override var height: Int = Config.blockSize

    //方向属性
    override var direction: Direction = Direction.DOWN

    override fun draw() = Painter.drawImage(when (direction) {
        Direction.UP -> "img/enemy_2_u.gif"
        Direction.DOWN -> "img/enemy_2_d.gif"
        Direction.LEFT -> "img/enemy_2_l.gif"
        Direction.RIGHT -> "img/enemy_2_r.gif"
    }, x, y)
}