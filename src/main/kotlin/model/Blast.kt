package model

import Config
import business.DestoryAble
import org.itheima.kotlin.game.core.Painter


/**
 * ClassName:Blast
 * Description:爆炸物
 */
class Blast(override var x: Int, override var y: Int) :DestoryAble {
    override fun needDestory(): Boolean {
        return index>=list.size
    }

    override val code: Int = 2
    override var width: Int = Config.blockSize
    override var height: Int = Config.blockSize
    //集合保存所有的图片
    val list = ArrayList<String>()
    //播放的角标
    var index = 0
    init {
        (1..32).forEach {
            list.add("img/blast_${it}.png")
        }
    }
    override fun draw() {
        index %= list.size
        val imgPath = list[index]
        Painter.drawImage(imgPath,x,y)
        index++
    }
}