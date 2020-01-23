<template>
  <b-list-group>
    <b-list-group-item style="text-align:center; background-color:lightgrey" >
      <strong >나의 장바구니</strong>
    </b-list-group-item>
    <b-list-group-item style="height:350px">
      <strong>기록</strong>
    </b-list-group-item>
    <b-list-group-item>
      <table style="width:100%; margin:auto">
        <tr>
          <td width="20%">
            <strong>인원</strong>
          </td>
          <td width="80%" style="text-align:right">{{this.$store.state.people}}명</td>
        </tr>
      </table>
    </b-list-group-item>
    <b-list-group-item>
      <table style="width:100%; margin:auto">
        <tr>
          <td width="20%">
            <strong>예산</strong>
          </td>
          <td width="80%" style="text-align:right">{{this.$store.state.money}}원</td>
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
          <td width="80%" style="text-align:right">원</td>
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
  </b-list-group>
</template>