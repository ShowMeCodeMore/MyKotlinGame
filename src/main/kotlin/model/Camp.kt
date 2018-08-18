package model

import Config
import business.AttackAble
import business.BlockAble
import business.DestoryAble
import business.SufferAble
import org.itheima.kotlin.game.core.Painter


/**
 * ClassName:Camp
 * Description:大本营
 */
class Camp(override var x: Int, override var y: Int) : BlockAble, SufferAble, DestoryAble {
    override fun destoryXg(): Array<View>? {
        return arrayOf(Blast(x - 32, y - 32)
                , Blast(x, y - 32)
                , Blast(x + 32, y - 32)
                , Blast(x - 32, y)
                , Blast(x, y)
                , Blast(x + 32, y)
                , Blast(x - 32, y + 32)
                , Blast(x, y + 32)
                , Blast(x + 32, y + 32))
    }

    override fun needDestory(): Boolean {
        return blood <= 0
    }

    override var blood: Int = 12

    override fun notifyAttack(attack: AttackAble): Array<View>? {
        blood -= attack.attackPower
        if (blood == 3 || blood == 6) {
            val x = x - 32
            val y = y - 32
            return arrayOf(Blast(x, y)
                    , Blast(x + 32, y)
                    , Blast(x + Config.blockSize, y)
                    , Blast(x + Config.blockSize + 32, y)
                    , Blast(x + Config.blockSize * 2, y)
                    , Blast(x, y + 32)
                    , Blast(x, y + Config.blockSize)
                    , Blast(x, y + Config.blockSize + 32)
                    , Blast(x + Config.blockSize * 2, y + 32)
                    , Blast(x + Config.blockSize * 2, y + Config.blockSize)
                    , Blast(x + Config.blockSize * 2, y + Config.blockSize + 32))
        }

        return null
    }

    override val code: Int = 2
    override var width: Int = 2 * Config.blockSize
    override var height: Int = 2 * Config.blockSize

    override fun draw() {
        if (blood <= 3) {//没有防护墙
            //x和y
            //width和height修改
            x = Config.gameWidth / 2 - 32
            y = Config.gameHeight - Config.blockSize
            width = Config.blockSize
            height = Config.blockSize
            Painter.drawImage("img/camp.gif", x, y)
        } else if (blood <= 6) {//砖墙
            Painter.drawImage("img/camp.gif", x + 32, y + 32)
            //绘制周围墙
            Painter.drawImage("img/wall_small.gif", x, y)
            Painter.drawImage("img/wall_small.gif", x + 32, y)
            Painter.drawImage("img/wall_small.gif", x + 64, y)
            Painter.drawImage("img/wall_small.gif", x + 96, y)
            Painter.drawImage("img/wall_small.gif", x, y + 32)
            Painter.drawImage("img/wall_small.gif", x, y + 64)
            Painter.drawImage("img/wall_small.gif", x + 96, y + 32)
            Painter.drawImage("img/wall_small.gif", x + 96, y + 64)
        } else {
            Painter.drawImage("img/camp.gif", x + 32, y + 32)
            //绘制周围墙
            Painter.drawImage("img/steel_small.gif", x, y)
            Painter.drawImage("img/steel_small.gif", x + 32, y)
            Painter.drawImage("img/steel_small.gif", x + 64, y)
            Painter.drawImage("img/steel_small.gif", x + 96, y)
            Painter.drawImage("img/steel_small.gif", x, y + 32)
            Painter.drawImage("img/steel_small.gif", x, y + 64)
            Painter.drawImage("img/steel_small.gif", x + 96, y + 32)
            Painter.drawImage("img/steel_small.gif", x + 96, y + 64)
        }

    }
}