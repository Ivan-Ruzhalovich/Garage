//package Garage.Configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import javax.sql.DataSource;
//
//
//@EnableWebSecurity
//public class MySecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    DataSource dataSource;
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource);
//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().
//                antMatchers("/home").hasRole("ADMINISTRATOR").
//                antMatchers("/Garage/**").hasRole("ADMINISTRATOR").
//                antMatchers("/**").
//                hasRole("USER")
//                .and().formLogin().permitAll();
////        http.authorizeRequests().antMatchers("/home").
////                hasRole("USER").
////                and().formLogin().permitAll();
//    }
//}
