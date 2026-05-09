#!/usr/bin/env python3
import os
import requests
import sys
import time


def main():
    env = os.getenv("NACOS_CREATE_TOKEN")
    if env and env.strip() and env.lower() == "true":
        print("NACOS_CREATE_TOKEN is true, creating token...")

        # Wait for Nacos to be fully ready
        time.sleep(5)

        # Login to get token
        login_url = "http://127.0.0.1:8080/v3/auth/user/login"
        login_data = {
            "username": "nacos",
            "password": "nacos"
        }

        try:
            login_headers = {
                "Content-Type": "application/x-www-form-urlencoded"
            }
            login_response = requests.post(login_url, data=login_data, headers=login_headers)
            login_result = login_response.json()
            token = login_result["accessToken"]
            print(f"Token obtained successfully")

            # Create config with token
            config_url = "http://127.0.0.1:8080/v3/console/cs/config"
            headers = {
                "accessToken": token,
                "Content-Type": "application/x-www-form-urlencoded"
            }
            config_data = {
                "dataId": "token.yml",
                "group": "DEFAULT_GROUP",
                "groupName": "DEFAULT_GROUP",
                "content": f"nacos:\n  token: {token}",
                "type": "yaml"
            }

            config_response = requests.post(config_url, data=config_data, headers=headers)
            result = config_response.json()
            print(f"Config creation result: {result}")

            if not result or "data" not in result or not result["data"]:
                raise RuntimeError("create token failed")

            print("Token config created successfully")
        except Exception as e:
            print(f"Error: {e}")
            sys.exit(1)
    else:
        print("NACOS_CREATE_TOKEN is not true, skipping token creation")


if __name__ == "__main__":
    main()
