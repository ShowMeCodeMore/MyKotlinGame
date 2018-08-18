package model

import Config
import org.itheima.kotlin.game.core.Painter

/**
 * ClassName:Wall
 * Description:草
 */
class Grass(override var x: Int, override var y: Int) : View {
    override val code: Int = 2
    override var width: Int = Config.blockSize
    override var height: Int = Config.blockSize

    override fun draw() {
        Painter.drawImage("img/grass.gif", x, y)
    }
}