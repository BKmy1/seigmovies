package com.example.seigmovies.config;

import com.example.seigmovies.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity  // 开启MVC security安全支持
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 重写configure(HttpSecurity http)方法，进行用户授权管理
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1、自定义用户访问控制
        http.authorizeRequests()
                .antMatchers("/**", "/css/**", "/fonts/**", "/images/**", "/login/**").permitAll()
                .antMatchers("/js/**", "/lib/layui/**","/*.css").permitAll()
                .antMatchers("/backTalk/**", "/backVideo/**").hasRole("admin")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .headers().frameOptions().disable();
        http.formLogin()
                .loginPage("/login").permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/toIndex", true)
                .failureUrl("/login?error")
                .and()
                .csrf()
                .disable();
    }


    /**
     * 重写configure(AuthenticationManagerBuilder auth)方法，进行自定义用户认证
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //  密码需要设置编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//         使用 UserDetailsService 进行身份认证
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }
}

