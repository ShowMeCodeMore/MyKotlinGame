package model

import Config
import business.*
import enums.Direction
import org.itheima.kotlin.game.core.Painter


/**
 * ClassName:Tank
 * Description:坦克
 */
class Tank(override var x: Int, override var y: Int) : MoveAble, BlockAble, SufferAble, DestoryAble {
    override fun needDestory(): Boolean {
        return blood <= 0
    }

    override var blood: Int = 20

    override fun notifyAttack(attack: AttackAble): Array<View>? {
        blood -= attack.attackPower
        return arrayOf(Blast(x, y))
    }

    override fun notifyCollision(block: BlockAble?, badDirection: Direction?) {
        //保存碰撞物 和碰撞的方向
        this.badBlock = block
        this.badDirection = badDirection
    }

    override val code: Int = 1
    override var width: Int = Config.blockSize
    override var height: Int = Config.blockSize
    //方向属性
    override var direction: Direction = Direction.UP

    //速度
    override val speed: Int = 15

    //碰撞物
    override var badBlock: BlockAble? = null
    //碰撞方向
    override var badDirection: Direction? = null

    /**
     * 移动或者改变方向
     * @param dir 设置的方向
     */
    fun move(dir: Direction) {
        //如果移动的方向和当前方向不一样  先改变方向  不用移动
        if (direction != dir) {
            //改变方向
            this.direction = dir
            return
        }
        //判断有没有碰撞
        if (direction == badDirection) return
        //移动
        when (direction) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }
        //越界处理

    }

    override fun draw() = Painter.drawImage(when (direction) {
        Direction.UP -> "img/tank_u.gif"
        Direction.DOWN -> "img/tank_d.gif"
        Direction.LEFT -> "img/tank_l.gif"
        Direction.RIGHT -> "img/tank_r.gif"
    }, x, y)

    /**
     * 发射子弹
     * @return 子弹对象
     */
    fun shot(): Bullet {
        //子弹的宽度和高度 需要Bullet对象提供
        //子弹的x和y坐标需要通过坦克计算得出

        //计算x和y
        var bulletX = 0
        var bulletY = 0
        //子弹的宽度和高度
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
}