<template>
  <div class="tag-wall">
    <panel :title="'标签墙'">
      <div slot="content" class="content">
        <svg :width='width' :height='height' @mousemove='listener($event)'>
          <a :href="tag.href" v-for='(tag,index) in tagList' :key="tag.href">
            <text :x='tag.x' :y='tag.y' :font-size='12 * (600/(600-tag.z))' :fill-opacity='((400+tag.z)/600)' :color='index | mapTagColor' >{{tag.text}}</text>
          </a>
        </svg>
      </div>
    </panel>
  </div>
</template>

<script type="text/ecmascript-6">
const TagCloud = require('TagCloud');
import Panel from '@/components/utils/Panel'
import {mixin} from '@/utils/index'

export default {
  data () {
    return {
      tagList: [],
      width:300,//svg宽度
      height:300,//svg高度
      tagsNum:20,//标签数量
      RADIUS:85.7,//球的半径
      speedX:Math.PI/360,//球一帧绕x轴旋转的角度
      speedY:Math.PI/360,//球-帧绕y轴旋转的角度
    }
  },
  computed:{
    CX(){//球心x坐标
      return this.width/2-50;
    },
    CY(){//球心y坐标
      return this.height/2;
    }
  },
  mixins: [mixin],
  components: {
    'panel': Panel
  },
  created () {
    this.listTag();

  },
  mounted(){
    /*let container = document.getElementById('tagCloud')
    TagCloud(container, this.tagList, this.tagCloud);*/
    setInterval(() => {
      this.rotateX(this.speedX);
      this.rotateY(this.speedY);
    }, 25)
  },
  methods: {
    listTag () {
      this.$http({
        url: this.$http.adornUrl('/article/searchTag'),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({data}) => {
        if (data && data.status === 0) {
          this.tagList = data.result.data
          let tags=[];
          for(let i = 0; i < this.tagList.length; i++){
            let tag = {};
            let k = -1 + (2 * (i + 1) - 1) / this.tagList.length;
            let a = Math.acos(k);
            let b = a * Math.sqrt(this.tagList.length * Math.PI)//计算标签相对于球心的角度
            // debugger
            tag.text = this.tagList[i];
            tag.x = this.CX +  this.RADIUS * Math.sin(a) * Math.cos(b);//根据标签角度求出标签的x,y,z坐标
            tag.y = this.CY +  this.RADIUS * Math.sin(a) * Math.sin(b);
            tag.z = this.RADIUS * Math.cos(a);
            tag.href = '/articles/search'+ '?keywords=' + tag.text;//给标签添加链接
            tags.push(tag);
          }
          this.tagList = tags;//让vue替我们完成视图更新
        }
      })
    },
    rotateX(angleX){
      var cos = Math.cos(angleX);
      var sin = Math.sin(angleX);
      for(let tag of this.tagList){
        var y1 = (tag.y- this.CY) * cos - tag.z * sin  + this.CY;
        var z1 = tag.z * cos + (tag.y- this.CY) * sin;
        tag.y = y1;
        tag.z = z1;
      }
    },
    rotateY(angleY){
      var cos = Math.cos(angleY);
      var sin = Math.sin(angleY);
      for(let tag of this.tagList){
        var x1 = (tag.x - this.CX) * cos - tag.z * sin + this.CX;
        var z1 = tag.z * cos + (tag.x-this.CX) * sin;
        tag.x = x1;
        tag.z = z1;
      }
    },
    listener(event){//响应鼠标移动
      var x = event.clientX - this.CX;
      var y = event.clientY - this.CY;
      this.speedX = x*0.0001>0 ? Math.min(this.RADIUS*0.0001, x*0.0001) : Math.max(-this.RADIUS*0.0001, x*0.0001);
      this.speedY = y*0.0001>0 ? Math.min(this.RADIUS*0.0001, y*0.0001) : Math.max(-this.RADIUS*0.0001, y*0.0001);
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/index.styl";

  .tag-wall
    .content
      padding 5px 20px
  .live-for {
    float: left;
    margin-left: 15px;
  }
</style>
