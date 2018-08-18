package business

import enums.Direction
import model.View


/**
 * ClassName:AutoMoveAble
 * Description:自动移动的能力
 */
interface AutoMoveAble:View {
    //速度
    val speed:Int
    //移动的方向
    val direction:Direction

    /**
     * 自动移动的方法
     */
    fun autoMove()
}