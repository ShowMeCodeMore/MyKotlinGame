package business

import model.View


/**
 * ClassName:AttackAble
 * Description:攻击能力
 */
interface AttackAble:View {
    //所有者
    val owner:View
    //攻击力
    var attackPower:Int
    /**
     * 检测是否发生碰撞
     * @return true 碰撞 false 没有碰撞
     */
    fun checkAttack(suffer:SufferAble):Boolean

    /**
     * 通知受到攻击了
     */
    fun notifyAttack(suffer:SufferAble)
}