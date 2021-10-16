import InfoDichVu from "./InfoDichVu";
import StepByStep from "./StepByStep";
import ShowDetailLichLamViec from "./ShowDetailLichLamViec";
import InfoKhachHang from "./InfoKhachHang";


export default function TabChiTietLichLamViec() {
    return (
        <div className="container mt-5">
            {/* Chi tiết hồ sơ khám */}
                <ShowDetailLichLamViec/>
            {/*End Chi tiết hồ sơ khám */}
           
            {/* Trạng thái */}
            <div className="row border border-dark mt-5">
                <StepByStep/>
            </div>
            {/* End Trạng thái */}

            {/* Thông Tin Khách Hàng*/}
                <InfoKhachHang/>
            {/*End Thông Tin Khách Hàng */}

            {/* Thông tin dịch vụ */}
                <InfoDichVu/>
            {/*End Thông tin dịch vụ */}

        </div>
    )
}