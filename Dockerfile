FROM gcr.io/distroless/static
ADD target/dataservice ./dataservice
CMD ["./dataservice", "--spring.profiles.active=production"]
