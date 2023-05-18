package com.magic.officeapp.ui.navigation

sealed class Screen(val route: String) {

    object LoadingScreen : Screen("loading_screen")
    object ProfileScreen : Screen("profile_screen")
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")

    object HRHomeScreen : Screen("hr_home_screen")
    object HREmployeeListScreen : Screen("hr_employee_list_screen")
    object HRAddEmployeeScreen : Screen("hr_add_employee_screen")

    // User
    object HomeScreen : Screen("home_screen")
    object AttendanceScreen : Screen("attendance_screen")
    object AnnouncementScreen : Screen("announcement_screen")
    object RequestScreen : Screen("request_screen")

    object RequestDetailScreen : Screen("request_detail_screen"){
        const val REQUEST_ID = "request_id"
    }
    object RequestFormScreen : Screen("request_form_screen")
    object PayrollScreen : Screen("payroll_screen")
    object PayrollDetailScreen : Screen("payroll_detail_screen")

    // Hr
    object HrHomeScreen : Screen("hr_home_screen")
    object HrAttendanceScreen : Screen("hr_attendance_screen")
    object HrAnnouncementScreen : Screen("hr_announcement_screen")
    object HrAnnouncementFormScreen : Screen("hr_announcement_form_screen")
    object HrRequestScreen : Screen("hr_request_screen")
    object HrRequestDetailScreen : Screen("hr_request_detail_screen")

    object HrPayrollScreen : Screen("hr_payroll_screen")
}