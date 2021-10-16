import InfoDichVu from "./InfoDichVu";
import StepByStep from "./StepByStep";
import ShowDetailHoSoKham from "./ShowDetailHoSoKham";
import InfoNhaSi from "./InfoNhaSi";


export default function TabChiTietHoSoKham() {
    return (
        <div className="container mt-5">
            {/* Chi tiết hồ sơ khám */}
                <ShowDetailHoSoKham/>
            {/*End Chi tiết hồ sơ khám */}
           
            <div className="row border border-dark mt-5">
                <StepByStep/>
            </div>

            {/* Thông Tin Nha Sĩ */}
                <InfoNhaSi/>
            {/*End Thông Tin Nha Sĩ */}

            {/* Thông tin dịch vụ */}
                <InfoDichVu/>
            {/*End Thông tin dịch vụ */}

        </div>
    )
}