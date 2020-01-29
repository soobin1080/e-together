<template>
  <b-list-group>
    <b-list-group-item style="text-align:center; background-color:lightgrey" >
      <strong >장보기 내역</strong>
    </b-list-group-item>
    <b-list-group-item class="product_table" style="height:350px">
      
      <table style="width:100%; margin:auto"  >
         <col width="70%" />
              <col width="30%" />
        <thead>
                <tr style="text-align:center">
                  <th>항목</th>
                  <th>수량</th>
                </tr>
              </thead>
              <tbody>
        <tr>
          <td class="product">
            {{this.$store.state.title}}
          </td>
          <td style="text-align:right">
           <input type="number" size="small" style="text-align:center; width:85%">
            </td>
        </tr>
              </tbody>
      </table>
      <br>
    </b-list-group-item>
    <b-list-group-item>
      <table style="width:100%; margin:auto">
        <tr>
          <td width="20%">
            <strong>인원</strong>
          </td>
          <td width="80%" style="text-align:right"><input type="number" :step="10" id="people" v-model="people" @input="triggerEvent" style="text-align:right">명</td>
        </tr>
      </table>
    </b-list-group-item>
    <b-list-group-item>
      <table style="width:100%; margin:auto">
        <tr>
          <td width="20%">
            <strong>예산</strong>
          </td>
          <td width="80%" style="text-align:right"><input type="number" :step="10000" id="money" v-model="money" @input="triggerEvent" style="text-align:right">원</td>
        </tr>
      </table>
    </b-list-group-item>
    <b-list-group-item>
      <table style="width:100%; margin:auto">
        <tr>
          <td width="20%">
            <strong>차트</strong>
          </td>
          <td width="80%" style="text-align:right">
            <table  style="radius:2; width:100%; margin:auto; text-align:center; background-color:#d9eeff">
              <col width="80px" />
              <col width="100px" />
              <thead>
                <tr>
                  <th>카테고리</th>
                  <th>비율</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>고기</td>
                  <td></td>
                </tr>
                <tr>
                  <td>야채</td>
                  <td></td>
                </tr>
                <tr>
                  <td>밥/라면</td>
                  <td></td>
                </tr>
                <tr>
                  <td>음료</td>
                  <td></td>
                </tr>
                <tr>
                  <td>과자</td>
                  <td></td>
                </tr>
              </tbody>
            </table>
          </td>
        </tr>
      </table>
      
    </b-list-group-item>
    <b-list-group-item>
      <table style="width:100%; margin:auto">
        <tr>
          <td width="20%">
            <strong>합계</strong>
          </td>
          <td width="80%" style="text-align:right">{{total}}원</td>
        </tr>
      </table>
    </b-list-group-item>
    <b-list-group-item>
      <table style="width:100%; margin:auto">
        <tr>
          <td width="20%">
            <strong>잔액</strong>
          </td>
          <td width="80%" style="text-align:right">원</td>
        </tr>
      </table>
    </b-list-group-item>
    <v-btn @click="list_submit">저장하기</v-btn>
  </b-list-group>
  
</template>
<script>
export default {
 data: () => ({
    total:"",
    people: "",
    money: "",   
       
  }),  
  created(){
    this.people=this.people;
    this.money=this.money;
  },
  mounted(){
    this.people=this.$store.state.people;
    this.money=this.$store.state.money;
  },
  methods:{
    triggerEvent(){
    this.$store.state.people=this.people;
    this.$store.state.money=this.money;
    },
    cal(){
      this.total+=(this.$store.state.body)*1;
    },
    list_submit(){
      // DB에 저장(Sub III때 구현)
    }
  }
}
</script>
<style>
.product{
  display: inline-block; 
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.product_table{
  overflow-y:scroll; overflow-x:hidden; width:100%;
}

</style>