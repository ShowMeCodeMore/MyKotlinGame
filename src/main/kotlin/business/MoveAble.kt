package business

import Config
import enums.Direction
import model.View


/**
 * ClassName:MoveAble
 * Description:移动能力
 */
interface MoveAble:View {
    //碰撞物
    var badBlock: BlockAble?
    //碰撞方向
    var badDirection: Direction?
    //方向属性
    var direction: Direction
    //速度
    val speed: Int
    /**
     * 判断有没有碰撞
     * @param block 阻挡物
     * @return 返回阻挡的方向  如果没有阻挡 返回null
     */
    fun willCollision(block:BlockAble):Direction?{
        //碰撞逻辑的检测
//        if(y>=block.y+block.height){
//            return null
//        }else if(y+height<=block.y){
//            return null
//        }else if(block.x+block.width<=x){
//            return null
//        }else if(x+width<=block.x){
//            return null
//        }
        //预计下一步移动位置
        var x = this.x
        var y = this.y
        //下一步的位置
        when (direction) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }

        //和阻挡物的检测
        if (y >= block.y + block.height || y + height <= block.y || block.x + block.width <= x || x + width <= block.x) {
            //边界碰撞检测
            if (x < 0||x > 12 * Config.blockSize||y < 0||y > 12 * Config.blockSize) {
                return direction
            }
            return null
        }
        //
//        if (x < 0) {
//            x = 0
//        } else if (x > 12 * Config.blockSize) {
//            x = 12 * Config.blockSize
//        }
//        if (y < 0) {
//            y = 0
//        } else if (y > 12 * Config.blockSize) {
//            y = 12 * Config.blockSize
//        }

        return direction//向下就碰撞
    }

    /**
     * 通知发生碰撞了
     * @param block 碰撞物
     * @param badDirection 碰撞方向
     */
    fun notifyCollision(block:BlockAble?,badDirection:Direction?){
        //保存碰撞物 和碰撞的方向
        this.badBlock = block
        this.badDirection = badDirection
    }
}