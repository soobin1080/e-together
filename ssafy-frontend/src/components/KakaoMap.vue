<template>
  <div ref="map"></div>
</template>

<script>
/* global kakao */
import emartmarker from "../assets/marker.png"
export default {
  name: "KakaoMap",
  props: {
    appkey: {
      type: String,
      required: true
    },
    width: {
      type: Number,
      default: 400
    },
    height: {
      type: Number,
      default: 300
    },
    center: {
      type: Array,
      required: true
    },
    level: {
      type: Number
    }
  },

  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=918cb2cf36b91b4f11f0a63002dc2154&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    }
  },

  data() {
    return {
      map: null,
      mapCenter: this.center
    };
  },

  watch: {
    isShowing(val) {
      if (val) {
        this.relayout();
      }
    },
    width(val) {
      this.$refs.map.style.width = val + "px";
      this.relayout();
    },
    height(val) {
      this.$refs.map.style.height = val + "px";
      this.relayout();
    }
  },

  methods: {
      
    initMap() {
      const container = this.$refs.map;
      container.style.width = this.width + "px";
      container.style.height = this.height + "px";



      const options = {
        center: new kakao.maps.LatLng(...this.mapCenter), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
      };

      

      const map = new kakao.maps.Map(container, options);
    //   map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
      kakao.maps.event.addListener(map, "center_changed", this.onCenterChanged);
      this.map = map;

      // 장소 검색 객체를 생성합니다
      const ps = new kakao.maps.services.Places(map);

      // 카테고리로 은행을 검색합니다
      ps.keywordSearch("이마트", this.placesSearchCB);
    },
    relayout() {
      kakao.maps.event.removeListener(
        this.map,
        "center_changed",
        this.onCenterChanged
      );
      this.map.relayout();
      const latlng = new kakao.maps.LatLng(...this.mapCenter);
      this.map.setCenter(latlng);
      kakao.maps.event.addListener(
        this.map,
        "center_changed",
        this.onCenterChanged
      );
    },
    onCenterChanged() {
      const center = this.map.getCenter();
      this.mapCenter = [center.getLat(), center.getLng()];
    },
    // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        var bounds = new kakao.maps.LatLngBounds();

        for (var i = 0; i < data.length; i++) {
          this.displayMarker(data[i]);
          bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        this.map.setBounds(bounds);
      }
    },

    // 지도에 마커를 표시하는 함수입니다
    displayMarker(place) {
      var imageSrc = emartmarker, // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(30, 40), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(place.y, place.x),
        image: markerImage // 마커이미지 설정
      });

      //   // 마커를 생성하고 지도에 표시합니다
      //   var marker = new kakao.maps.Marker({
      //     map: this.map,
      //     position: new kakao.maps.LatLng(place.y, place.x)
      //   });

        const infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

      // 마커에 클릭이벤트를 등록합니다
      kakao.maps.event.addListener(marker, "click", function() {
        
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent(
          '<div style="padding:5px;font-size:12px;">' +
            place.place_name +
            "</div>"
        );
        infowindow.open(this.map, marker);
      });
    }
  }
};
</script>