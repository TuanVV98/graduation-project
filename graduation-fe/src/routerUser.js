import Register from "components/Auth/DangKy/Register";
import DangNhap from "components/Auth/Login/DangNhap";
import ThongTinNguoiDung from "components/User/AccountInfo/ThongTinNguoiDung";
import DatLich from "components/User/DatLich/DatLich";
import ShowDetailNhaSi from "components/User/Dentist/ShowDetailNhaSi";
import TrangChu from "components/User/HomePage/TrangChu";
import HoSoKham from "components/User/HoSoKham/HoSoKham";
import TinTucChiTiet from "components/User/TinTuc/TinTucChiTiet";

let routerUser = [
  {
    path: "/",
    name: "Trang chủ",
    component: TrangChu,
    layout: "/user",
  },
  {
    path: "/datich",
    name: "Đặt lịch",
    component: DatLich,
    layout: "/user",
  },
  {
    path: "/nhasi",
    name: "Nha sĩ",
    component: ShowDetailNhaSi,
    layout: "/user",
  },
  {
    path: "/tintuc",
    name: "Tin tức",
    component: TinTucChiTiet,
    layout: "/user",
  },
  {
    path: "/hosokham",
    name: "Hồ sơ khám",
    component: HoSoKham,
    layout: "/user",
  },
  {
    path: "/taikhoan/dangnhap",
    name: "Đăng nhập",
    component: DangNhap,
    layout: "/user",
  },
  {
    path: "/taikhoan/dangky",
    name: "Đăng ký",
    component: Register,
    layout: "/user",
  },
  {
    path: "/taikhoan/thongtinnguoidung",
    name: "Thông tin người dùng",
    component: ThongTinNguoiDung,
    layout: "/user",
  }
];

export default routerUser;
