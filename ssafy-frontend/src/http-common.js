import axios from "axios";

export default axios.create({
//  baseURL: "http://13.209.9.53:8197/together/api" // aws서버
 baseURL: "http://192.168.31.106:8197/together/api" // 수빈서버

//  headers: {
//   "Content-type": "application/xml",
// }
});