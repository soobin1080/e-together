<template>
  <div>
      <!-- <button @click="toggle">{{ showMap ? "hide" : "show" }}</button>
      <button @click="changeMapWidth">change map width</button>
      <button @click="changeMapHeight">change map height</button> -->
      <!-- <KakaoMap
        v-show="showMap"
        :appkey="appkey"
        :center="center"
        :width="mapWidth"
        :height="mapHeight"
        :isShowing="showMap"
      ></KakaoMap> -->
      <KakaoMap       
        :appkey="appkey"
        :center="center"
        :width="mapWidth"
        :height="mapHeight"        
      ></KakaoMap>
  </div>
</template>

<script>
import KakaoMap from '../components/KakaoMap';
export default {
    data() {
        return  {
            showMap: false,
            appkey:'xxxxxxxx',
            center:[33.450701, 126.570667],
            mapWidth: 1800,
            mapHeight: 600
        }
    },

    components: {
        KakaoMap
    },

    // for test
    // methods: {
    //     toggle() {
    //         this.showMap = !this.showMap;
    //     },
    //     changeMapWidth() {
    //         this.mapWidth = this.mapWidth > 200 ? 200 : 500;
    //     },
    //     changeMapHeight() {
    //         this.mapHeight = this.mapHeight > 300 ? 300 : 800;
    //     }
    // }
}
</script>