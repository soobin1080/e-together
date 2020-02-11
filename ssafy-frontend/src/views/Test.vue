<template>
  <div>
    <table width="100%" style="font-size:15px">
      <tr style="text-align:center;">
        <th>제목</th>
        <td style="text-align:center">0 명</td>

        <th>인원</th>
        <td style="text-align:center">0 명</td>

        <th>예산</th>
        <td style="text-align:center">0 원</td>

        <th>날짜</th>
        <td style="text-align:center">000</td>

        <th>적/부</th>
        <td style="text-align:center">
          <div>
            <v-btn class="ma-2" text icon color="blue lighten-2" @click="suitable(1)">
              <v-icon small>mdi-thumb-up</v-icon>
            </v-btn>

            <v-btn class="ma-2" text icon color="red lighten-2" @click="suitable(2)">
              <v-icon small>mdi-thumb-down</v-icon>
            </v-btn>000
          </div>
        </td>
      </tr>
    </table>
    <button @click="makePDF('body')">PDF</button>
    <div
      data-html2canvas-ignore="true"
    >출력하지 않고 싶은 영역은 태그에 'data-html2canvas-ignore' attribute를 넣어주면된다.</div>
  </div>
</template>
<script>
import html2canvas from "html2canvas";
import jsPDF from "jspdf";

export default {
  name: "pdf",
  data() {
    return {
      propTitle: "mypdf"
    };
  },
  methods: {
    makePDF(selector) {
      console.log(selector);
      window.html2canvas = html2canvas; //Vue.js 특성상 window 객체에 직접 할당해야한다.
      let that = this;
      let pdf = new jsPDF("p", "mm", "a4");
      let canvas = pdf.canvas;
      const pageWidth = 210; //캔버스 너비 mm
      const pageHeight = 295; //캔버스 높이 mm
      canvas.width = pageWidth;

      let ele = document.querySelector(selector);
      let width = ele.offsetWidth; // 셀렉트한 요소의 px 너비
      let height = ele.offsetHeight; // 셀렉트한 요소의 px 높이
      let imgHeight = (pageWidth * height) / width; // 이미지 높이값 px to mm 변환

      if (!ele) {
        console.warn(selector + " is not exist.");
        return false;
      }

      console.log(ele);

    //   var canvasElement = document.createElement("canvas");
      html2canvas(ele, { canvaspdf:canvas }).then(function(canvaspdf) {
        const img = canvaspdf.toDataURL("image/jpeg", 1.0);
        pdf.addImage(img, "jpeg", 0,0, pageWidth, imgHeight);
        pdf.save(that.propTitle.toLowerCase() + ".pdf");
      });
     
    }
  }
};
</script>