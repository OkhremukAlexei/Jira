package com.jira.pojo.dto;

import com.jira.models.User;

public class UserDto {
    private Long id;

    private String login;
    private String password;
    private String roles;
    private AccountDto accountDto;

    public UserDto() {
    }

    public UserDto(Long id, String login, String password, String roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public UserDto(Long id, String login, String password, String roles, AccountDto accountDto) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.roles = roles;
        this.accountDto = accountDto;
    }

    public UserDto(String login, String password, String roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public static UserDto build(User user){

        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getRole(),
                new AccountDto(
                        user.getAccount().getId(),
                        user.getAccount().getName(),
                        user.getAccount().getSurname(),
                        user.getAccount().getEmail())
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public AccountDto getAccount() {
        return accountDto;
    }

    public void setAccountDto(AccountDto accountDto) {
        this.accountDto = accountDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;

        UserDto userDto = (UserDto) o;

        if (!id.equals(userDto.id)) return false;
        if (!login.equals(userDto.login)) return false;
        if (!password.equals(userDto.password)) return false;
        if (!roles.equals(userDto.roles)) return false;
        return accountDto.equals(userDto.accountDto);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + roles.hashCode();
        result = 31 * result + accountDto.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                ", account=" + accountDto +
                '}';
    }
}
