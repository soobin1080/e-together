<template>
  <div>
       <FindMap></FindMap>
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
import FindMap from '../components/FindMap';
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
        KakaoMap,
        FindMap
    },

}
</script>